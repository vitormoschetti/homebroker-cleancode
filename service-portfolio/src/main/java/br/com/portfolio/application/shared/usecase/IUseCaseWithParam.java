package br.com.portfolio.application.shared.usecase;

public interface IUseCaseWithParam<Inp, Out> {

    Out execute(Inp param);

}
