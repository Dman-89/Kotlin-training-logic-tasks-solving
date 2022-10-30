package annotations_processing

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


fun main() {
    val context = SpringApplication.run(Main::class.java)
    val counter = context.getBean(Counter::class.java)
    println("main.count()")
    counter.count() // with profiling
    println("main ended")
}

@SpringBootApplication
open class Main