package br.com.portfolio.infra.shared.factory;

import br.com.portfolio.infra.shared.entity.IEntity;

public interface IFactory<Model, Entity extends IEntity> {

    Model toModel(Entity entity);

    Entity toEntity(Model model);

}
