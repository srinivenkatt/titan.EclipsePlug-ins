/******************************************************************************
 * Copyright (c) 2000-2017 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.titan.runtime.core;

import java.util.ArrayList;

/**
 * TTCN-3 float template
 * @author Farkas Izabella Ingrid  
 *
 * Not yet complete rewrite
 */
public class TitanFloat_template extends Base_Template {
	// int_val part
	// TODO maybe should be renamed in core
	private TitanFloat single_value;

	// value_list part
	private ArrayList<TitanFloat_template> value_list;

	// value range part
	private boolean min_is_present, max_is_present;
	private boolean min_is_exclusive, max_is_exclusive;
	private TitanFloat min_value, max_value;


	public TitanFloat_template() {
		// do nothing
	}

	public TitanFloat_template(final template_sel otherValue) {
		super(otherValue);
		checkSingleSelection(otherValue);
	}

	public TitanFloat_template(final double otherValue) {
		super(template_sel.SPECIFIC_VALUE);
		single_value = new TitanFloat(otherValue);
	}

	public TitanFloat_template(final TitanFloat otherValue) {
		super(template_sel.SPECIFIC_VALUE);
		otherValue.mustBound("Creating a template from an unbound float value.");

		single_value = new TitanFloat(otherValue);
	}

	public TitanFloat_template(final TitanFloat_template otherValue) {
		copyTemplate(otherValue);
	}

	// originally clean_up
	public void cleanUp() {
		switch (templateSelection) {
		case SPECIFIC_VALUE:
			single_value = null;
			break;
		case VALUE_LIST:
		case COMPLEMENTED_LIST:
			value_list.clear();
			value_list = null;
		case VALUE_RANGE:
			min_value = null;
			max_value = null;
		default:
			break;
		}
		templateSelection = template_sel.UNINITIALIZED_TEMPLATE;
	}

	// originally operator=
	public TitanFloat_template assign(final template_sel otherValue) {
		checkSingleSelection(otherValue);
		cleanUp();
		setSelection(otherValue);

		return this;
	}

	// originally operator=
	public TitanFloat_template assign(final double otherValue) {
		cleanUp();
		setSelection(template_sel.SPECIFIC_VALUE);
		single_value = new TitanFloat(otherValue);

		return this;
	}

	// originally operator=
	public TitanFloat_template assign(final TitanFloat otherValue) {
		otherValue.mustBound("Assignment of an unbound float value to a template.");

		cleanUp();
		setSelection(template_sel.SPECIFIC_VALUE);
		single_value = new TitanFloat(otherValue);

		return this;
	}

	// originally operator=
	public TitanFloat_template assign(final TitanFloat_template otherValue) {
		if (otherValue != this) {
			cleanUp();
			copyTemplate(otherValue);
		}

		return this;
	}

	private void copyTemplate(final TitanFloat_template otherValue) {
		switch (otherValue.templateSelection) {
		case SPECIFIC_VALUE:
			single_value = new TitanFloat(otherValue.single_value);
			break;
		case OMIT_VALUE:
		case ANY_VALUE:
		case ANY_OR_OMIT:
			break;
		case VALUE_LIST:
		case COMPLEMENTED_LIST:
			value_list = new ArrayList<TitanFloat_template>(otherValue.value_list.size());
			for (int i = 0; i < otherValue.value_list.size(); i++) {
				final TitanFloat_template temp = new TitanFloat_template(otherValue.value_list.get(i));
				value_list.add(temp);
			}
			break;
		case VALUE_RANGE:
			min_is_present = otherValue.min_is_present;
			min_is_exclusive = otherValue.min_is_exclusive;
			if (min_is_present) {
				min_value = new TitanFloat(otherValue.min_value);
			}
			max_is_present = otherValue.max_is_present;
			max_is_exclusive = otherValue.max_is_exclusive;
			if (max_is_present) {
				max_value = new TitanFloat(otherValue.max_value);
			}
			break;
		default:
			throw new TtcnError("Copying an uninitialized/unsupported float template.");
		}

		setSelection(otherValue);
	}

	// originally match
	public TitanBoolean match(final TitanFloat otherValue) {
		return match(otherValue, false);
	}

	// originally match
	public TitanBoolean match(final TitanFloat otherValue, final boolean legacy) {
		if (!otherValue.isBound()) {
			return new TitanBoolean(false);
		}

		switch (templateSelection) {
		case SPECIFIC_VALUE:
			return single_value.operatorEquals(otherValue);
		case OMIT_VALUE:
			return new TitanBoolean(false);
		case ANY_VALUE:
		case ANY_OR_OMIT:
			return new TitanBoolean(true);
		case VALUE_LIST:
		case COMPLEMENTED_LIST:
			for (int i = 0; i < value_list.size(); i++) {
				if (value_list.get(i).match(otherValue, legacy).getValue()) {
					return new TitanBoolean(templateSelection == template_sel.VALUE_LIST);
				}
			}
			return new TitanBoolean(templateSelection == template_sel.COMPLEMENTED_LIST);
		case VALUE_RANGE: {
			boolean lowerMissMatch = true;
			boolean upperMissMatch = true;
			if (min_is_present) {
				if (min_is_exclusive) {
					lowerMissMatch = min_value.isLessThanOrEqual(otherValue).getValue();
				} else {
					lowerMissMatch = min_value.isLessThan(otherValue).getValue();
				}
			}
			if (max_is_present) {
				if (max_is_exclusive) {
					upperMissMatch = min_value.isGreaterThanOrEqual(otherValue).getValue();
				} else {
					upperMissMatch = min_value.isGreaterThan(otherValue).getValue();
				}
			}
			return new TitanBoolean(lowerMissMatch && upperMissMatch);
		}
		default:
			throw new TtcnError("Matching with an uninitialized/unsupported float template.");
		}
	}

	public void setType(template_sel templateType) {
		setType(templateType, 0);
	}

	public void setType(template_sel templateType, int listLength) {
		cleanUp();
		switch (templateType) {
		case VALUE_LIST:
		case COMPLEMENTED_LIST:
			setSelection(templateType);
			value_list = new ArrayList<TitanFloat_template>(listLength);
			for (int i = 0; i < listLength; ++i) {
				value_list.add(new TitanFloat_template());
			}
			break;
		case VALUE_RANGE:
			setSelection(template_sel.VALUE_RANGE);
			min_is_present = false;
			max_is_present = false;
			min_is_exclusive = false;
			max_is_exclusive = false;
			break;
		default:
			throw new TtcnError("Setting an invalid type for a float template.");
		}
	}

	public TitanFloat_template list_item(final int listIndex) {
		if (templateSelection != template_sel.VALUE_LIST && templateSelection != template_sel.COMPLEMENTED_LIST) {
			throw new TtcnError("Accessing a list element of a non-list float template.");
		}
		if (listIndex < 0) {
			throw new TtcnError("Accessing an bitstring value list template using a negative index (" + listIndex + ").");
		}
		if (listIndex >= value_list.size()) {
			throw new TtcnError("Index overflow in a float value list template.");
		}

		return value_list.get(listIndex);
	}

	public void setMin(double minValue) {
		if (templateSelection != template_sel.VALUE_RANGE) {
			throw new TtcnError("Float template is not range when setting lower limit.");
		}
		if (max_is_present && min_is_present && max_value.isLessThan(min_value).getValue()) {
			throw new TtcnError("The lower limit of the range is greater than the " + "upper limit in a float template.");
		}

		min_is_present = true;
		min_is_exclusive = false;
		min_value = new TitanFloat(minValue);
	}

	public void setMin(final TitanFloat minValue) {
		minValue.mustBound("Using an unbound value when setting the lower bound " + "in a float range template.");

		setMin(minValue.getValue());
	}

	public void setMax(double maxValue) {
		if (templateSelection != template_sel.VALUE_RANGE) {
			throw new TtcnError("Float template is not range when setting upper limit.");
		}
		if (min_is_present && max_is_present && min_value.isGreaterThan(max_value).getValue()) {
			throw new TtcnError("The upper limit of the range is smaller than the " + "lower limit in a float template.");
		}

		max_is_present = true;
		max_is_exclusive = false;
		max_value = new TitanFloat(maxValue);
	}

	public void setMax(final TitanFloat maxValue) {
		maxValue.mustBound("Using an unbound value when setting the upper bound " + "in a float range template.");

		setMax(maxValue.getValue());
	}

	public void setMinExclusive(boolean minExclusive) {
		if (templateSelection != template_sel.VALUE_RANGE) {
			throw new TtcnError("Float template is not range when setting lower limit exclusiveness.");
		}

		min_is_exclusive = minExclusive;
	}

	public void setMaxExclusive(boolean maxExclusive) {
		if (templateSelection != template_sel.VALUE_RANGE) {
			throw new TtcnError("Float template is not range when setting upper limit exclusiveness.");
		}

		max_is_exclusive = maxExclusive;
	}

	public TitanFloat valueOf() {
		if (templateSelection != template_sel.SPECIFIC_VALUE || is_ifPresent) {
			throw new TtcnError("Performing a valueof or send operation on a non-specific float template.");
		}

		return single_value;
	}

	public TitanBoolean isPresent() {
		return isPresent(false);
	}

	public TitanBoolean isPresent(boolean legacy) {
		if (templateSelection == template_sel.UNINITIALIZED_TEMPLATE) {
			return new TitanBoolean(false);
		}

		return match_omit(legacy).not();
	}

	public TitanBoolean match_omit() {
		return match_omit(false);
	}

	public TitanBoolean match_omit(boolean legacy) {
		if (is_ifPresent) {
			return new TitanBoolean(true);
		}

		switch (templateSelection) {
		case OMIT_VALUE:
		case ANY_OR_OMIT:
			return new TitanBoolean(true);
		case VALUE_LIST:
		case COMPLEMENTED_LIST:
			if (legacy) {
				for (int i = 0; i < value_list.size(); i++) {
					if (value_list.get(i).match_omit().getValue()) {
						return new TitanBoolean(templateSelection == template_sel.VALUE_LIST);
					}
				}
				return new TitanBoolean(templateSelection == template_sel.COMPLEMENTED_LIST);
			}
			return new TitanBoolean(false);
		default:
			return new TitanBoolean(false);
		}
	}
}
