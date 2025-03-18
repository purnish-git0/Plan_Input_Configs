package interfaces;

import entity.Input;

import java.util.Set;

public interface ExternalConstraint {

    public Set<Input> getExternallyConstrainingFields();
}
