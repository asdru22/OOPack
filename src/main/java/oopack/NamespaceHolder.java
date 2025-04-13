package oopack;

public abstract class NamespaceHolder implements Buildable {
    private final String namespace;
    protected Project project;

    public NamespaceHolder(String namespace) {
        this.namespace = namespace;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
