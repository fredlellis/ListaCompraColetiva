package listacompracoletiva

class ListaCompra {

    private String nome;
    private Integer limiteMinimo;
    private Integer limiteMaximo;
    private Usuario organizador;
    private Map<Usuario, Produto> fornedorProduto;
    private List<Distribudor> distribuidores;
    private Map<Usuario, Produto> compradorProduto;
    private Rating rating
    private Date dataDeFechamento;
    private Date dataDePublicacao;
    private Frete frete;


    static constraints = {
    }
}
