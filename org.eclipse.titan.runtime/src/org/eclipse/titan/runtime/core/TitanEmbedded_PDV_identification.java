/******************************************************************************
 * Copyright (c) 2000-2018 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/epl-2.0/EPL-2.0.html
 ******************************************************************************/
package org.eclipse.titan.runtime.core;

import java.text.MessageFormat;

import org.eclipse.titan.runtime.core.RAW.RAW_enc_tr_pos;
import org.eclipse.titan.runtime.core.RAW.RAW_enc_tree;
import org.eclipse.titan.runtime.core.TTCN_EncDec.coding_type;
import org.eclipse.titan.runtime.core.TTCN_EncDec.error_type;
import org.eclipse.titan.runtime.core.TTCN_EncDec.raw_order_t;


/**
 * Part of the representation of the ASN.1 EMBEDDED PDV type
 *
 * @author Kristof Szabados
 */
public class TitanEmbedded_PDV_identification extends Base_Type {
	public enum union_selection_type { UNBOUND_VALUE,  ALT_syntaxes,  ALT_syntax,  ALT_presentation__context__id,  ALT_context__negotiation,  ALT_transfer__syntax,  ALT_fixed };
	private TitanEmbedded_PDV_identification.union_selection_type union_selection;
	//originally a union which can not be mapped to Java
	private Base_Type field;
	public TitanEmbedded_PDV_identification() {
		union_selection = union_selection_type.UNBOUND_VALUE;
	};
	public TitanEmbedded_PDV_identification(final TitanEmbedded_PDV_identification otherValue) {
		copy_value(otherValue);
	};

	private void copy_value(final TitanEmbedded_PDV_identification otherValue) {
		switch (otherValue.union_selection){
		case ALT_syntaxes:
			field = new TitanEmbedded_PDV_identification_syntaxes((TitanEmbedded_PDV_identification_syntaxes)otherValue.field);
			break;
		case ALT_syntax:
			field = new TitanObjectid((TitanObjectid)otherValue.field);
			break;
		case ALT_presentation__context__id:
			field = new TitanInteger((TitanInteger)otherValue.field);
			break;
		case ALT_context__negotiation:
			field = new TitanEmbedded_PDV_identification_context__negotiation((TitanEmbedded_PDV_identification_context__negotiation)otherValue.field);
			break;
		case ALT_transfer__syntax:
			field = new TitanObjectid((TitanObjectid)otherValue.field);
			break;
		case ALT_fixed:
			field = new TitanAsn_Null((TitanAsn_Null)otherValue.field);
			break;
		default:
			throw new TtcnError("Assignment of an unbound union value of type EMBEDDED PDV.identification.");
		}
		union_selection = otherValue.union_selection;
	}

	//originally operator=
	public TitanEmbedded_PDV_identification assign( final TitanEmbedded_PDV_identification otherValue ) {
		if (otherValue != this) {
			cleanUp();
			copy_value(otherValue);
		}

		return this;
	}
	@Override
	public TitanEmbedded_PDV_identification assign( final Base_Type otherValue ) {
		if (otherValue instanceof TitanEmbedded_PDV_identification) {
			return assign((TitanEmbedded_PDV_identification)otherValue);
		}
		throw new TtcnError("Internal Error: value can not be cast to EMBEDDED PDV.identification.");
	}

	//originally clean_up
	public void cleanUp() {
		field = null;
		union_selection = union_selection_type.UNBOUND_VALUE;
	}

	public boolean isChosen(final TitanEmbedded_PDV_identification.union_selection_type checked_selection) {
		if(checked_selection == union_selection_type.UNBOUND_VALUE) {
			throw new TtcnError("Internal error: Performing ischosen() operation on an invalid field of union type EMBEDDED PDV.identification.");
		}
		return union_selection == checked_selection;
	}

	@Override
	public boolean isBound() {
		return union_selection != union_selection_type.UNBOUND_VALUE;
	}

	@Override
	public boolean isValue() {
		switch (union_selection) {
		case UNBOUND_VALUE:
			return false;
		case ALT_syntaxes:
			return field.isValue();
		case ALT_syntax:
			return field.isValue();
		case ALT_presentation__context__id:
			return field.isValue();
		case ALT_context__negotiation:
			return field.isValue();
		case ALT_transfer__syntax:
			return field.isValue();
		case ALT_fixed:
			return field.isValue();
		default:
			throw new TtcnError("Invalid selection in union is_bound");
		}
	}

	@Override
	public boolean isPresent() {
		return isBound();
	}

	//originally operator==
	public boolean operatorEquals( final TitanEmbedded_PDV_identification otherValue ) {
		if (union_selection == union_selection_type.UNBOUND_VALUE) {
			throw new TtcnError( "The left operand of comparison is an unbound value of union type EMBEDDED PDV.identification." );
		}
		if (otherValue.union_selection == union_selection_type.UNBOUND_VALUE) {
			throw new TtcnError( "The right operand of comparison is an unbound value of union type EMBEDDED PDV.identification." );
		}
		if (union_selection != otherValue.union_selection) {
			return false;
		}
		switch (union_selection) {
		case ALT_syntaxes:
			return ((TitanEmbedded_PDV_identification_syntaxes)field).operatorEquals((TitanEmbedded_PDV_identification_syntaxes)otherValue.field);
		case ALT_syntax:
			return ((TitanObjectid)field).operatorEquals((TitanObjectid)otherValue.field);
		case ALT_presentation__context__id:
			return ((TitanInteger)field).operatorEquals((TitanInteger)otherValue.field);
		case ALT_context__negotiation:
			return ((TitanEmbedded_PDV_identification_context__negotiation)field).operatorEquals((TitanEmbedded_PDV_identification_context__negotiation)otherValue.field);
		case ALT_transfer__syntax:
			return ((TitanObjectid)field).operatorEquals((TitanObjectid)otherValue.field);
		case ALT_fixed:
			return ((TitanAsn_Null)field).operatorEquals((TitanAsn_Null)otherValue.field);
		default:
			return false;
		}
	}
	@Override
	public boolean operatorEquals( final Base_Type otherValue ) {
		if (otherValue instanceof TitanEmbedded_PDV_identification) {
			return operatorEquals((TitanEmbedded_PDV_identification)otherValue);
		}
		throw new TtcnError("Internal Error: value can not be cast to EMBEDDED PDV.identification.");
	}

	//originally operator!=
	public boolean operatorNotEquals( final TitanEmbedded_PDV_identification otherValue ) {
		return !operatorEquals(otherValue);
	}

	public TitanEmbedded_PDV_identification_syntaxes getsyntaxes() {
		if (union_selection != union_selection_type.ALT_syntaxes) {
			cleanUp();
			field = new TitanEmbedded_PDV_identification_syntaxes();
			union_selection = union_selection_type.ALT_syntaxes;
		}
		return (TitanEmbedded_PDV_identification_syntaxes)field;
	}

	public TitanEmbedded_PDV_identification_syntaxes constGetsyntaxes() {
		if (union_selection != union_selection_type.ALT_syntaxes) {
			throw new TtcnError("Using non-selected field syntaxes in a value of union type EMBEDDED PDV.identification.");
		}
		return (TitanEmbedded_PDV_identification_syntaxes)field;
	}

	public TitanObjectid getsyntax() {
		if (union_selection != union_selection_type.ALT_syntax) {
			cleanUp();
			field = new TitanObjectid();
			union_selection = union_selection_type.ALT_syntax;
		}
		return (TitanObjectid)field;
	}

	public TitanObjectid constGetsyntax() {
		if (union_selection != union_selection_type.ALT_syntax) {
			throw new TtcnError("Using non-selected field syntax in a value of union type EMBEDDED PDV.identification.");
		}
		return (TitanObjectid)field;
	}

	public TitanInteger getpresentation__context__id() {
		if (union_selection != union_selection_type.ALT_presentation__context__id) {
			cleanUp();
			field = new TitanInteger();
			union_selection = union_selection_type.ALT_presentation__context__id;
		}
		return (TitanInteger)field;
	}

	public TitanInteger constGetpresentation__context__id() {
		if (union_selection != union_selection_type.ALT_presentation__context__id) {
			throw new TtcnError("Using non-selected field presentation-context-id in a value of union type EMBEDDED PDV.identification.");
		}
		return (TitanInteger)field;
	}

	public TitanEmbedded_PDV_identification_context__negotiation getcontext__negotiation() {
		if (union_selection != union_selection_type.ALT_context__negotiation) {
			cleanUp();
			field = new TitanEmbedded_PDV_identification_context__negotiation();
			union_selection = union_selection_type.ALT_context__negotiation;
		}
		return (TitanEmbedded_PDV_identification_context__negotiation)field;
	}

	public TitanEmbedded_PDV_identification_context__negotiation constGetcontext__negotiation() {
		if (union_selection != union_selection_type.ALT_context__negotiation) {
			throw new TtcnError("Using non-selected field context-negotiation in a value of union type EMBEDDED PDV.identification.");
		}
		return (TitanEmbedded_PDV_identification_context__negotiation)field;
	}

	public TitanObjectid gettransfer__syntax() {
		if (union_selection != union_selection_type.ALT_transfer__syntax) {
			cleanUp();
			field = new TitanObjectid();
			union_selection = union_selection_type.ALT_transfer__syntax;
		}
		return (TitanObjectid)field;
	}

	public TitanObjectid constGettransfer__syntax() {
		if (union_selection != union_selection_type.ALT_transfer__syntax) {
			throw new TtcnError("Using non-selected field transfer-syntax in a value of union type EMBEDDED PDV.identification.");
		}
		return (TitanObjectid)field;
	}

	public TitanAsn_Null getfixed() {
		if (union_selection != union_selection_type.ALT_fixed) {
			cleanUp();
			field = new TitanAsn_Null();
			union_selection = union_selection_type.ALT_fixed;
		}
		return (TitanAsn_Null)field;
	}

	public TitanAsn_Null constGetfixed() {
		if (union_selection != union_selection_type.ALT_fixed) {
			throw new TtcnError("Using non-selected field fixed in a value of union type EMBEDDED PDV.identification.");
		}
		return (TitanAsn_Null)field;
	}

	public TitanEmbedded_PDV_identification.union_selection_type get_selection() {
		return union_selection;
	}
	public void log() {
		switch (union_selection) {
		case ALT_syntaxes:
			TTCN_Logger.log_event_str("{ syntaxes := ");
			field.log();
			TTCN_Logger.log_event_str(" }");
			break;
		case ALT_syntax:
			TTCN_Logger.log_event_str("{ syntax := ");
			field.log();
			TTCN_Logger.log_event_str(" }");
			break;
		case ALT_presentation__context__id:
			TTCN_Logger.log_event_str("{ presentation-context-id := ");
			field.log();
			TTCN_Logger.log_event_str(" }");
			break;
		case ALT_context__negotiation:
			TTCN_Logger.log_event_str("{ context-negotiation := ");
			field.log();
			TTCN_Logger.log_event_str(" }");
			break;
		case ALT_transfer__syntax:
			TTCN_Logger.log_event_str("{ transfer-syntax := ");
			field.log();
			TTCN_Logger.log_event_str(" }");
			break;
		case ALT_fixed:
			TTCN_Logger.log_event_str("{ fixed := ");
			field.log();
			TTCN_Logger.log_event_str(" }");
			break;
		default:
			TTCN_Logger.log_event_unbound();
			break;
		}
	}
	@Override
	public void set_implicit_omit() {
		switch (union_selection) {
		case ALT_syntaxes:
		case ALT_syntax:
		case ALT_presentation__context__id:
		case ALT_context__negotiation:
		case ALT_transfer__syntax:
		case ALT_fixed:
			field.set_implicit_omit();
			break;
		default:
			break;
		}
	}

	@Override
	public void encode_text(final Text_Buf text_buf) {
		switch (union_selection) {
		case ALT_syntaxes:
			text_buf.push_int(0);
			break;
		case ALT_syntax:
			text_buf.push_int(1);
			break;
		case ALT_presentation__context__id:
			text_buf.push_int(2);
			break;
		case ALT_context__negotiation:
			text_buf.push_int(3);
			break;
		case ALT_transfer__syntax:
			text_buf.push_int(4);
			break;
		case ALT_fixed:
			text_buf.push_int(5);
			break;
		default:
			throw new TtcnError("Text encoder: Encoding an unbound value of union type EMBEDDED PDV.identification.");
		}
		field.encode_text(text_buf);
	}

	@Override
	public void decode_text(final Text_Buf text_buf) {
		final int temp = text_buf.pull_int().getInt();
		switch (temp) {
		case 0:
			getsyntaxes().decode_text(text_buf);
			break;
		case 1:
			getsyntax().decode_text(text_buf);
			break;
		case 2:
			getpresentation__context__id().decode_text(text_buf);
			break;
		case 3:
			getcontext__negotiation().decode_text(text_buf);
			break;
		case 4:
			gettransfer__syntax().decode_text(text_buf);
			break;
		case 5:
			getfixed().decode_text(text_buf);
			break;
		default:
			throw new TtcnError("Text decoder: Unrecognized union selector was received for type EMBEDDED PDV.identification.");
		}
	}

	@Override
	public void encode(final TTCN_Typedescriptor p_td, final TTCN_Buffer p_buf, final coding_type p_coding, final int flavour) {
		switch (p_coding) {
		case CT_RAW: {
			final TTCN_EncDec_ErrorContext errorContext = new TTCN_EncDec_ErrorContext("While RAW-encoding type '%s': ", p_td.name);
			if (p_td.raw == null) {
				TTCN_EncDec_ErrorContext.error_internal("No RAW descriptor available for type '%s'.", p_td.name);
			}
			final RAW_enc_tr_pos rp = new RAW_enc_tr_pos(0, null);
			final RAW_enc_tree root = new RAW_enc_tree(true, null, rp, 1, p_td.raw);
			RAW_encode(p_td, root);
			root.put_to_buf(p_buf);
			errorContext.leaveContext();
			break;
		}
		default:
			throw new TtcnError(MessageFormat.format("Unknown coding method requested to encode type `{0}''", p_td.name));
		}
	}

	@Override
	public void decode(final TTCN_Typedescriptor p_td, final TTCN_Buffer p_buf, final coding_type p_coding, final int flavour) {
		switch (p_coding) {
		case CT_RAW: {
			final TTCN_EncDec_ErrorContext errorContext = new TTCN_EncDec_ErrorContext("While RAW-decoding type '%s': ", p_td.name);
			if (p_td.raw == null) {
				TTCN_EncDec_ErrorContext.error_internal("No RAW descriptor available for type '%s'.", p_td.name);
			}
			raw_order_t order;
			switch (p_td.raw.top_bit_order) {
			case TOP_BIT_LEFT:
				order = raw_order_t.ORDER_LSB;
				break;
			case TOP_BIT_RIGHT:
			default:
				order = raw_order_t.ORDER_MSB;
				break;
			}
			final int rawr = RAW_decode(p_td, p_buf, p_buf.get_len() * 8, order);
			if (rawr < 0) {
				final error_type temp = error_type.values()[-rawr];
				switch (temp) {
				case ET_INCOMPL_MSG:
				case ET_LEN_ERR:
					TTCN_EncDec_ErrorContext.error(temp, "Can not decode type '%s', because invalid or incomplete message was received", p_td.name);
					break;
				case ET_UNBOUND:
				default:
					TTCN_EncDec_ErrorContext.error(error_type.ET_INVAL_MSG, "Can not decode type '%s', because invalid or incomplete message was received", p_td.name);
					break;
				}
			}
			errorContext.leaveContext();
			break;
		}
		default:
			throw new TtcnError(MessageFormat.format("Unknown coding method requested to decode type `{0}''", p_td.name));
		}
	}

	//TODO: implement set_param !
}