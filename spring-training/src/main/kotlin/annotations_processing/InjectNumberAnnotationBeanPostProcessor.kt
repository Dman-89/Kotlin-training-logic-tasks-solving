package annotations_processing

import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.isAccessible

@Component
class InjectNumberAnnotationBeanPostProcessor : BeanPostProcessor {
    //https://stackoverflow.com/questions/57582135/kotlin-annotation-not-in-fielddecleration-or-compiled-java
    //https://stackoverflow.com/questions/28391889/kotlin-reflection-get-list-of-fields
    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any {
        val fields = bean::class.declaredMemberProperties
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
}