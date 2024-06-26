package br.com.portfolio.domain.shared.event;

public interface IDispatcher<E extends IEvent> {

    void dispatch(E event);

}
