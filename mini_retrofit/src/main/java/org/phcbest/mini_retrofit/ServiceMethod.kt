package org.phcbest.mini_retrofit

import org.phcbest.mini_retrofit.annotation.Field
import org.phcbest.mini_retrofit.annotation.GET
import java.lang.reflect.Method

/**
 * 构建接口实例,解析注解内容
 */
class ServiceMethod {

    private var mBuilder: Builder? = null

    constructor(builder: Builder) {
        this.mBuilder = builder
    }

    /**
     * 获取网络请求的类型
     */
    fun getMethodName(): String {
        return mBuilder?.methodName!!
    }

    /**
     * 进行url参数拼接
     */
    fun getBaseUrl(): String {
        if (mBuilder?.methodName == "GET") {
            val sb = StringBuffer()
            sb.append(mBuilder?.miniRetrofit?.getBaseUrl())
                .append(mBuilder?.relativeUrl)
            val parameterMap = getParameter()

            if (parameterMap != null) {
                val keySet = parameterMap.keys
                //添加url的?带参符
                if (keySet.isNotEmpty()) {
                    sb.append("?")
                }
                //添加参数
                for (key in keySet) {
                    sb.append(key)
                        .append("=")
                        .append(parameterMap[key])
                        .append("&")
                }
                //删除末尾的&号
                sb.deleteCharAt(sb.length - 1)
            }
            return sb.toString()
        }
        return mBuilder?.miniRetrofit!!.getBaseUrl()
    }

    private fun getParameter(): Map<String, Any>? {
        return mBuilder?.parameterMap
    }

    /**
     * 解析注解
     */
    class Builder {
        var miniRetrofit: MiniRetrofit? = null
        var method: Method? = null

        //方法注解
        var methodAnnotations: Array<Annotation>? = null

        //参数注解
        var parameterAnnotationsArray: Array<Array<Annotation>>? = null

        //参数键值对
        var parameterMap: MutableMap<String, Any> = mutableMapOf()
        private var args: Array<Any>? = null

        var methodName: String? = null
        var relativeUrl: String? = null


        constructor(miniRetrofit: MiniRetrofit, method: Method, args: Array<Any>) {
            this.miniRetrofit = miniRetrofit
            //方法注解列表
            this.method = method
            this.methodAnnotations = method.annotations
            //方法参数注解列表
            this.parameterAnnotationsArray = method.parameterAnnotations
            //方法参数内容
            this.args = args
        }

        fun build(): ServiceMethod {
            //遍历方法注解
            for (methodAnnotation in methodAnnotations!!) {
                parseMethodAnnotation(methodAnnotation)
            }
            //遍历方法参数注解
            for ((index, parameterAnnotations) in parameterAnnotationsArray!!.withIndex()) {
                parseParameter(index, parameterAnnotations);
            }

            return ServiceMethod(this)
        }

        /**
         * 解析方法参数注解
         * @param index 方法参数值的index
         * @param parameterAnnotations 方法参数注解数组
         */
        private fun parseParameter(index: Int, parameterAnnotations: Array<Annotation>) {
            val value: Any? = args?.get(index)
            //遍历参数注解
            for (annotations in parameterAnnotations) {
                //判断注解类型
                if (annotations is Field) {
                    parameterMap[annotations.value] = value as Any
                }
            }
        }

        /**
         * 解析方法注解
         * 获取方法注解中的值
         */
        private fun parseMethodAnnotation(methodAnnotation: Annotation) {
            if (methodAnnotation is GET) {
                parseHttpMethodAndPath("GET", (methodAnnotation as GET).value)
            }
        }

        private fun parseHttpMethodAndPath(httpMethod: String, value: String) {
            if (httpMethod == "GET") {
                methodName = "GET"
                this.relativeUrl = value
            }
        }


    }

}