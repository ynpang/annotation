package com.enjoy.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {

	private static final long serialVersionUID = -5575893900970589345L;

	private String id;

	private String userName;

}
