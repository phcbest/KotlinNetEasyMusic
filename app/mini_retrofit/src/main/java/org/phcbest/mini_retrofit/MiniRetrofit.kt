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

    //使用map缓存retrofit解析后的方法
    private val servicesMethodCache: Map<Method, ServiceMethod> = ConcurrentHashMap()

    private var baseURl: String? = null

    constructor(baseURl: String) {
        this.baseURl = baseURl
    }

    fun getBaseUrl(): String {
        return this.baseURl ?: ""
    }

    /**
     * 构建接口实例
     */
    fun <T> create(sercice: Class<T>): T {
        //动态代理
        return Proxy.newProxyInstance(sercice.classLoader, arrayOf<Class<*>>(sercice),
            object : InvocationHandler {
                override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
                    //1 验证是否接口
                    require(sercice.isInterface) {
                        throw IllegalArgumentException("API declarations must be interfaces.")
                    }
                    //2 进行构建接口实例

                    TODO()
                    //3 调用http框架进行处理
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