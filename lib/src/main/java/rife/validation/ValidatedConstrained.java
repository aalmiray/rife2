/*
 * Copyright 2001-2022 Geert Bevin <gbevin[remove] at uwyn dot com>
 * Licensed under the Apache License, Version 2.0 (the "License")
 */
package rife.validation;

import java.util.Collection;
import java.util.List;

/**
 * This interface extends the Validated interface and provides additional
 * methods that are related to beans that are constrained with {@link
 * ConstrainedProperty} constraints.
 * <p>One notable addition is the capability to handle {@link ValidationGroup}s.
 *
 * @author Geert Bevin (gbevin[remove] at uwyn dot com)
 * @see Validated
 * @see Constrained
 * @see ConstrainedProperty
 * @see ValidationGroup
 * @since 1.4
 */
public interface ValidatedConstrained<P extends ConstrainedProperty> extends Validated {
    /**
     * Adds a new validation group.
     *
     * @param name the name of the validation group that needs to be created
     *             and added
     * @return the newly created <code>ValidationGroup</code>
     * @since 1.4
     */
    ValidationGroup<P> addGroup(String name);

    /**
     * Focuses on one particular validation group, showing only the
     * <code>ValidationError</code>s that were generated by its
     * <code>ValidationRule</code>s.
     *
     * @param name the name of the validation group that will be focused
     * @since 1.4
     */
    void focusGroup(String name);

    /**
     * Removed all the <code>ValidationError</code>s of a particular
     * validation group.
     *
     * @param name the name of the validation group that will be focused
     * @since 1.4
     */
    void resetGroup(String name);

    /**
     * Retrieves all validation groups.
     *
     * @return the collection of all registered validation groups
     * @since 1.4
     */
    Collection<ValidationGroup<P>> getGroups();

    /**
     * Retrieve a particular validation group.
     *
     * @param name the name of the validation group that will be retrieved
     * @return the requested <code>ValidationGroup</code>; or
     * <p><code>null</code> if no such validation group exists
     * @since 1.4
     */
    ValidationGroup<P> getGroup(String name);

    /**
     * Validate the <code>ValidationRule</code>s of a particular validation
     * group.
     *
     * @param name the name of the validation group that will be retrieved
     * @return <code>true</code> if no validation errors were generated; or
     * <p><code>false</code> otherwise
     * @since 1.4
     */
    boolean validateGroup(String name);

    /**
     * Validate the <code>ValidationRule</code>s of a particular validation
     * group and also validates the entire bean within the provided
     * <code>ValidationContext</code>
     *
     * @param name    the name of the validation group
     * @param context the <code>ValidationContext</code> in which this bean
     *                instance will be additionally validated
     * @return <code>true</code> if no validation errors were generated; or
     * <p><code>false</code> otherwise
     * @since 1.6
     */
    boolean validateGroup(String name, ValidationContext context);

    /**
     * Adds the validation rules that are related to a particular {@link
     * ConstrainedProperty}.
     * <p>If the rules of this property name have already been added before
     * through another <code>ConstrainedProperty</code> instance, its existing
     * <code>ValidationRule</code>s will be erased and the previous
     * constraints will be merged into the new
     * <code>ConstrainedProperty</code> before adding its validation rules.
     *
     * @param constrainedProperty the ConstrainedProperty that will be
     *                            inspected
     * @return the list of generated {@link ValidationRule}s
     * @since 1.4
     */
    List<PropertyValidationRule> addConstrainedPropertyRules(P constrainedProperty);

    /**
     * Generates the validation rules that are related to a particular {@link
     * ConstrainedProperty}.
     *
     * @param constrainedProperty the ConstrainedProperty that will be
     *                            inspected
     * @return the list of generated {@link ValidationRule}s
     * @since 1.4
     */
    List<PropertyValidationRule> generateConstrainedPropertyRules(P constrainedProperty);

    /**
     * Returns the collection of error messages that occurred during the
     * loading of the content of a certain property.
     *
     * @param propertyName the name of the property whose loading errors
     *                     should be obtained
     * @return null if no errors occurred during the loading of the content of
     * the provided property or if the property doesn't exist; or
     * <p>the requested collection of error messages
     * @since 1.4
     */
    Collection<String> getLoadingErrors(String propertyName);
}
