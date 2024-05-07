import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TesteJPA {

    public static void main(String[] args) {
        EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();

        try {
            Produto produto = new Produto("Notebook", "Notebook Dell", 2500.0);
            manager.persist(produto);

            transaction.commit();


            Produto produtoRecuperado = manager.find(Produto.class, produto.getId());
            System.out.println("Produto recuperado: " + produtoRecuperado.getNome() + ", " + produtoRecuperado.getDescricao() + ", " + produtoRecuperado.getPreco());
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            manager.close();
            JPAUtil.closeEntityManagerFactory();
        }
    }
}