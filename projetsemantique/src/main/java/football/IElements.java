package football;

public interface IElements<E> {

    boolean containsElem();

    E getElemByURI();

    void addElem(E e);
}
