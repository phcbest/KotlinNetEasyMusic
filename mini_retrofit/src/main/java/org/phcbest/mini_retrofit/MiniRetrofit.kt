package org.phcbest.mini_retrofit

import java.lang.IllegalArgumentException
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.concurrent.ConcurrentHashMap


/**
 * 这个类是仿照retrofit的实现方法来进行开发的一个网络框架
 */
class MiniRetrofit {

    //使用map缓存retrofit解析后的方法,也就是缓存增强后的接口文件的
    private val servicesMethodCache: MutableMap<Method, ServiceMethod> = ConcurrentHashMap()

    private var baseURl: String? = null

    constructor(baseURl: String) {
        this.baseURl = baseURl
    }

    fun getBaseUrl(): String {
        return this.baseURl ?: ""
    }

    /**
     * retrofit的构建类
     */
    class Builder {
        private var baseUrl: String? = null

        public fun baseUrl(baseUrl: String): Builder {
            this.baseUrl = baseUrl
            return this
        }

        public fun build(): MiniRetrofit {
            //只需要判null,因为存在baseurl为空的情况
            if (baseUrl == null) {
                throw IllegalArgumentException("baseurl required")
            }
            return MiniRetrofit(baseUrl!!)
        }
    }

    /**
     * 构建接口实例
     * @param method 接口的接口方法
     * @param args 方法参数内容
     */
    private fun loadServiceMethod(method: Method, args: Array<Any>): ServiceMethod {
        var result = servicesMethodCache[method]
        if (result != null) {
            return result
        }
        //锁住servicesMethodCache
        synchronized(servicesMethodCache) {
            result = servicesMethodCache[method]
            if (result == null) {
                //使用ServiceMethod内置的构造器来创建一个新的method
                result = ServiceMethod.Builder(this, method, args).build()
                //将创建出来的新的method添加到数组中
                servicesMethodCache[method] = result!!
            }
        }
        return result!!
    }


    /**
     * 构建接口实例
     */
    fun <T> create(sercice: Class<T>): T {
        //动态代理
        return Proxy.newProxyInstance(sercice.classLoader, arrayOf<Class<*>>(sercice),
            //动态代理的实际处理
            object : InvocationHandler {
                override fun invoke(proxy: Any?, method: Method?, args: Array<Any>?): Any {
                    //1 验证是否接口
                    require(sercice.isInterface) {
                        throw IllegalArgumentException("API declarations must be interfaces.")
                    }
                    //2 进行构建接口实例
                    val serviceMethod = loadServiceMethod(method!!, args!!)
                    //3 调用http框架进行处理
                    val yepHttpCall = YepHttpCall(serviceMethod)

                    //该回调函数的返回
                    return yepHttpCall
                }
            }) as T

//        return Proxy.newProxyInstance(service.getClassLoader(), arrayOf<Class<*>>(service),
//            object : InvocationHandler() {
//                @Throws(Throwable::class)
//                operator fun invoke(
//                    proxy: Any?,
//                    method: Method?,
//                    args: Array<Any?>?,
//                ): Any? {
//
//                    //1 验证是否是接口
//                    require(service.isInterface()) { "API declarations must be interfaces." }
//
//                    //2 进行构建接口实例，同时进行方法注解、方法参数注解解析，这些参数解析完成以后，我们就可以把注解解析后的url进行拼接
//                    val serviceMethod: ServiceMethod = loadServiceMethod(method, args)
//
//                    //3 调用 http 框架执行网络请求
//                    return OkHttpCall(serviceMethod)
//                }
//            }
//        ) as T
    }

}