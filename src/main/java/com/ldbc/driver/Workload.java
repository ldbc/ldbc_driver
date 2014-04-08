package com.ldbc.driver;

import com.ldbc.driver.control.DriverConfiguration;
import com.ldbc.driver.control.WorkloadParams;
import com.ldbc.driver.generator.GeneratorFactory;

import java.util.Iterator;
import java.util.Map;

public abstract class Workload {
    private boolean isInitialized = false;
    private boolean isCleanedUp = false;

    private long operationCount;

    protected abstract Map<Class<? extends Operation<?>>, OperationClassification> operationClassificationMapping();

    /**
     * Called once to initialize state for workload
     */
    public final void init(DriverConfiguration params) throws WorkloadException {
        if (isInitialized) {
            throw new WorkloadException("Workload may be initialized only once");
        }
        isInitialized = true;
        this.operationCount = params.operationCount();
        onInit(params.asMap());
    }

    protected long getOperationCount() {
        return operationCount;
    }

    public abstract void onInit(Map<String, String> properties) throws WorkloadException;

    public final void cleanup() throws WorkloadException {
        if (isCleanedUp) {
            throw new WorkloadException("Workload may be cleaned up only once");
        }
        isCleanedUp = true;
        onCleanup();
    }

    protected abstract void onCleanup() throws WorkloadException;

    public final Iterator<Operation<?>> operations(GeneratorFactory generators)
            throws WorkloadException {
        if (WorkloadParams.UNBOUNDED_OPERATION_COUNT == getOperationCount()) {
            // Generate all workload operations before beginning
            return createOperations(generators);
        } else {
            // Generate all workload operations before beginning
            return generators.limit(createOperations(generators), getOperationCount());
        }
    }

    protected abstract Iterator<Operation<?>> createOperations(GeneratorFactory generators)
            throws WorkloadException;
}
