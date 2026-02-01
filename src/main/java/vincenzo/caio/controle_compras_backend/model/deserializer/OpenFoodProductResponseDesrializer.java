package vincenzo.caio.controle_compras_backend.model.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import vincenzo.caio.controle_compras_backend.model.OpenFoodProductResponse;

public class OpenFoodProductResponseDesrializer extends JsonDeserializer<OpenFoodProductResponse> {

    @Override
    public OpenFoodProductResponse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        OpenFoodProductResponse response = new OpenFoodProductResponse();
        response.setCodigoDeBarras(node.get("code").asText());

        JsonNode product = node.get("product");
        if (product != null) {
            response.setMarca(product.path("brands").asText(null));
            response.setNome(product.path("product_name").asText(null));
            response.setQuantidade(product.path("product_quantity").asInt(1));
            response.setUnidade(product.path("product_quantity_unit").asText(null));
        }

        return response;
    }


}
