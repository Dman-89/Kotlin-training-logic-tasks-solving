package annotations_processing

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class CounterImpl(@Value("\${counter.start}") var start: Int): Counter {

    @InjectNumber(3)
    private lateinit var times: Number

//    init {
//        println("Initializer block that prints start times: $times")
//        println("Initializer block that prints start value: $start")
//    }

    override fun count() {
        println(start)
        println(times)
    }
}