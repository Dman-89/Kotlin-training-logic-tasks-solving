package annotations_processing

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class CounterImpl(@Value("\${counter.start}") var start: Int): Counter {

    @InjectNumber(3) // need to add dependency "org.jetbrains.kotlin:kotlin-reflect"
//    @field:InjectNumber(3) // if use java tools (bean.javaClass.declaredFields)
    private var times: Number = 0
//    private lateinit var times: Number // won't work in initializer block

    init {
//        println("Phase 2")
        println("Initializer block that prints start times: $times") // 0
        println("Initializer block that prints start value: $start") // 1
    }

    override fun count() {
        println("After bean created and setup times value: $times") // 3
        println("After bean created and PostConstruct start value: $start") // 10
    }

    @PostConstruct
    fun updateStart() {
        start = 10
    }
}