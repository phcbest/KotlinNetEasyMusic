package org.phcbest.mini_retrofit

import java.lang.reflect.Method
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
        TODO()
    }

}