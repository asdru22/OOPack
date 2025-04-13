package oopack;

import java.util.HashMap;
import java.util.Map;

public abstract class NamespaceHolder<I,E> implements Buildable {
    private final String namespace;
    protected Project project;

    protected final Map<String, I> items = new HashMap<>();

    public NamespaceHolder(String namespace) {
        this.namespace = namespace;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public I add(E type, String name, String... contents) {
        return createAndStore(type, name, String.join("\n", contents));
    }

    public I add(E type, String name, StringBuilder content) {
        return createAndStore(type, name, content.toString());
    }

    public I get(String name) {
        return items.get(name);
    }

    protected abstract I createAndStore(E type, String name, String content);

}
