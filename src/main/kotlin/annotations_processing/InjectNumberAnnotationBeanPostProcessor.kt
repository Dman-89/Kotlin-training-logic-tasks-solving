package annotations_processing

import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component
import org.springframework.util.ReflectionUtils

@Component
class InjectNumberAnnotationBeanPostProcessor: BeanPostProcessor {

    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
//        return super.postProcessBeforeInitialization(bean, beanName)
        if (bean.javaClass == CounterImpl::class.java) {
            val fields = bean.javaClass.declaredFields
            fields.forEach {
                val injectNumber = it.getAnnotation(InjectNumber::class.java)
//                if (injectNumber != null) {
                    println("${bean.javaClass} has declaredFields: $fields")
//                    println("injecting number ${injectNumber.num}")
                    if (it.declaringClass == Number::class.java && it.trySetAccessible()) ReflectionUtils.setField(it, bean, injectNumber.num)
//                }
            }
        }
        return bean
    }
}