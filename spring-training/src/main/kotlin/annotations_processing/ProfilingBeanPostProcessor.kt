package annotations_processing

import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy

@Component
class ProfilingBeanPostProcessor(@Value("\${counter.profilingEnabled}") var profilingEnabled: Boolean): BeanPostProcessor {

    private val profilingBeansMap = mutableMapOf<String, Class<*>>()

    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
        if (!profilingEnabled) return super.postProcessBeforeInitialization(bean, beanName)
        if (bean.javaClass.getAnnotation(Profiling::class.java) != null)
            profilingBeansMap[beanName] = bean.javaClass;
        return super.postProcessBeforeInitialization(bean, beanName)
    }

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        if (!profilingEnabled) return super.postProcessBeforeInitialization(bean, beanName)
        val clazz = profilingBeansMap[beanName]
        if (clazz != null) {
            println("методы бина $beanName: " + bean.javaClass.declaredMethods[0].name)
            return Proxy.newProxyInstance(clazz.classLoader, clazz.interfaces,
                InvocationHandler { proxy, method, args ->
                    println("Профилирую")
                    val start = System.nanoTime()
                    val retVal = method.invoke(bean, args)
                    println("Время работы метода: ${System.nanoTime() - start}")
                    println("Завершение профилирования")
                    return@InvocationHandler retVal
                }
            )
        }
        return super.postProcessBeforeInitialization(bean, beanName)
    }
}