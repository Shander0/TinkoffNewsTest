package shander.tinkoffnewstest.utils;

public interface IConverter<S, D> {

    D convert(S src);

}
