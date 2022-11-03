package fialka.config

import com.fasterxml.jackson.databind.ObjectMapper
import fialka.dto.Product
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer


class ProductSerializer: Serializer<Product> {

    private val log = org.slf4j.LoggerFactory.getLogger(javaClass)
    private val objectMapper = ObjectMapper()

    override fun serialize(topic: String?, data: Product?): ByteArray {
        log.info("Serializing: $data")
        return objectMapper.writeValueAsBytes(
            data ?: throw SerializationException("Error when serializing Product to ByteArray[]"))

    }
}