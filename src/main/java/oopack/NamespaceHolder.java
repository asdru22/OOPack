package oopack;

public abstract class NamespaceHolder implements Buildable {
    private final String namespace;

    public NamespaceHolder(String namespace) {
        this.namespace = namespace;
    }

    public String getNamespace() {
        return namespace;
    }

}
