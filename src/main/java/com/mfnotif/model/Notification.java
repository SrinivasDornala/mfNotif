package com.mfnotif.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "order_id")
	private long orderId;
	@Column(name = "to")
	private String to;
	@Column(name = "type")
	private String type;
	@Column(name = "text")
	private String text;
	@Column(name = "subject")
	private String subject;
	@Column(name = "method")
	private String method;
	@Column(name = "notify")
	private String notify;
	@Column(name = "order_status")
	private String orderStatus;

}
