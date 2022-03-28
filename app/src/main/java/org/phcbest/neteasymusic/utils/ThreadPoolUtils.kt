package org.phcbest.neteasymusic.utils

import java.util.concurrent.*

class ThreadPoolUtils {
    /**
     * 创建链接式阻塞队列
     */
    private var mPoolWorkPool: BlockingQueue<Runnable> = LinkedBlockingQueue<Runnable>(128)

    //创建线程执行器
    var threadPoolExecutor: ThreadPoolExecutor =
        ThreadPoolExecutor(10, 30, 10, TimeUnit.MINUTES, mPoolWorkPool)


    companion object {
        fun newInstance(): ThreadPoolUtils {
            return ThreadPoolUtils()
        }
    }
}