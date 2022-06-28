package org.phcbest.mini_retrofit

import org.omg.CORBA.Object
import org.phcbest.mini_retrofit.annotation.GET
import java.lang.reflect.Method

/**
 * 构建接口实例,解析注解内容
 */
class ServiceMethod {

    companion object {
        class Builder {
            var miniRetrofit: MiniRetrofit? = null
            var method: Method? = null

            //方法注解
            var methodAnnotations: Array<Annotation>? = null

            //参数注解
            var parameterAnnotationsArray: Array<Array<Annotation>>? = null

            //参数键值对
            private var parameterMap: MutableMap<String, Object> = mutableMapOf()
            private var args: Array<Any>? = null

            private var methodName: String? = null
            private var relativeUrl: String? = null


            constructor(miniRetrofit: MiniRetrofit, method: Method, args: Array<Any>) {
                this.miniRetrofit = miniRetrofit
                //方法注解列表
                this.method = method
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

//                return ServiceMethod(this)
                TODO()
            }

            private fun parseParameter(index: Int, parameterAnnotations: Array<Annotation>) {
                TODO("Not yet implemented")
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
}