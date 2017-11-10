package my.component.converte;

public interface Converter<T,B> {
    T convert(T t,B b);
}
