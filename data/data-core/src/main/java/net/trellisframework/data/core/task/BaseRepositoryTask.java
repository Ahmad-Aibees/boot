package net.trellisframework.data.core.task;

import net.trellisframework.context.provider.InjectorBeanProvider;
import net.trellisframework.context.task.BaseTask;
import net.trellisframework.data.core.data.repository.GenericRepository;
import net.trellisframework.data.core.util.PagingModelMapper;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;

@Transactional(
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRES_NEW,
        rollbackFor = {TransactionSystemException.class}
)
public interface BaseRepositoryTask<R extends GenericRepository<?, ?>> extends BaseTask, PagingModelMapper, InjectorBeanProvider {

    default R getRepository() {
        return getBean((Class<R>) (((ParameterizedType) this.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0]));
    }


}
