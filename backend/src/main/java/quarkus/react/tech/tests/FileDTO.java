package quarkus.react.tech.tests;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.io.Serializable;

@RegisterForReflection
public record FileDTO(
        byte[] data,
        String filename,
        String datatype) implements Serializable {
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "filesize = " + data.length + "Bytes, " +
                "file = " + filename + "." + datatype +
                ")";
    }
}
