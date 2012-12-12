package com.ebiester.webtestrunner.bean;

import java.util.List;

public class ClassSpecsPair {
    private String className;
    private List<Spec> specs;

    public ClassSpecsPair(String className, List<Spec> specs) {
        setClassName(className);
        setSpecs(specs);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Spec> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Spec> specs) {
        this.specs = specs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(className + ": ");
        for (Spec spec : specs) {
            sb.append(spec.getSpec());
        }

        return sb.toString();
    }
}
