package kr.co.ensmart.frameworkdemo.common.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	private int id;
	private String userName;
	private String password;
	private String email;

}
