package vincenzo.caio.controle_compras_backend.model.enums;

public enum TipoUnidade {

    UNIDADE(""),
    ML("ml"),
    KG("kg"),
    GRAMAS("g");

    public String unidadeOpenFood;

    TipoUnidade(String unidadeOpenFood) {
        this.unidadeOpenFood = unidadeOpenFood;
    }

    /**
     * Converte uma String representando uma unidade OpenFood para o enum TipoUnidade correspondente.
     * @param unidade a String que representa a unidade OpenFood
     * @return o enum TipoUnidade correspondente ou null se não encontrar
     */
    public static TipoUnidade fromUnidadeOpenFood(String unidade) {
        if (unidade == null || unidade.isEmpty()) {
            return UNIDADE;
        }

        for (TipoUnidade tipo : TipoUnidade.values()) {
            if (tipo.unidadeOpenFood.equalsIgnoreCase(unidade)) {
                return tipo;
            }
        }

        return UNIDADE;
    }

    /**
     * Converte uma String para o enum TipoUnidade correspondente.
     * Tenta primeiro converter pelo nome do enum, depois pela unidadeOpenFood.
     * @param valor a String a ser convertida
     * @return o enum TipoUnidade correspondente
     * @throws IllegalArgumentException se não encontrar correspondência
     */
    public static TipoUnidade fromString(String valor) {
        if (valor == null || valor.isEmpty()) {
            return UNIDADE;
        }

        // Tenta converter pelo nome do enum
        try {
            return TipoUnidade.valueOf(valor.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Se não conseguir, tenta converter pela unidadeOpenFood
            TipoUnidade tipo = fromUnidadeOpenFood(valor);
            if (tipo != null) {
                return tipo;
            }

            // Se não encontrar correspondência, lança exceção
            throw new IllegalArgumentException("Tipo de unidade não reconhecido: " + valor);
        }
    }


}
