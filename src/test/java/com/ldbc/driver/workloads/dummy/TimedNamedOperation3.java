package com.ldbc.driver.workloads.dummy;

import com.ldbc.driver.temporal.Time;

public class TimedNamedOperation3 extends NothingOperation {
    private final String name;

    public TimedNamedOperation3(Time startTime, Time dependencyTime, String name) {
        setScheduledStartTime(startTime);
        setDependencyTime(dependencyTime);
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "TimedNamedOperation3{" +
                "scheduledStartTime=" + scheduledStartTime() +
                ", dependencyTime=" + dependencyTime() +
                ", name='" + name +
                "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TimedNamedOperation3 operation = (TimedNamedOperation3) o;

        if (dependencyTime() != null ? !dependencyTime().equals(operation.dependencyTime()) : operation.dependencyTime() != null)
            return false;
        if (name != null ? !name.equals(operation.name) : operation.name != null) return false;
        if (scheduledStartTime() != null ? !scheduledStartTime().equals(operation.scheduledStartTime()) : operation.scheduledStartTime() != null)
            return false;

        return true;
    }
}