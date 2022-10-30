package annotations_processing

import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component
import org.springframework.util.ReflectionUtils
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.isAccessible

@Component
class InjectNumberAnnotationBeanPostProcessor : BeanPostProcessor {
    //https://stackoverflow.com/questions/57582135/kotlin-annotation-not-in-fielddecleration-or-compiled-java
    //https://stackoverflow.com/questions/28391889/kotlin-reflection-get-list-of-fields
    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any {
        //        return super.postProcessBeforeInitialization(bean, beanName)
        val fields = bean::class.declaredMemberProperties
        if (bean !is Counter) return bean
        println("constructor phase 1")
        fields.filterIsInstance<KMutableProperty<*>>().forEach { field ->
            val injectNumber = field.findAnnotation<InjectNumber>()
            println("${bean.javaClass} has declaredFields: $fields")
            if (injectNumber != null) {
                field.isAccessible = true
                field.setter.call(bean, injectNumber.num)
            }
        }
        return bean
    }

    //second approach
//    if (bean::class.java == CounterImpl::class.java) {
//        val fields = bean.javaClass.declaredFields
//        fields.forEach {
//            val injectNumber = it.getAnnotation(InjectNumber::class.java)
//            if (injectNumber != null) {
//                println("${bean.javaClass} has declaredFields: $fields")
//                it.isAccessible = true
//                ReflectionUtils.setField(it, bean, injectNumber.num)
//            }
//        }
//    }
//    return bean


}