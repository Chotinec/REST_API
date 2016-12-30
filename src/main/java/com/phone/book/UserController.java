package com.phone.book;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	private Map<Integer, User> users = new ConcurrentHashMap<Integer, User>();

	private AtomicInteger ids = new AtomicInteger(0);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<User> list() {
		return users.values();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getById(@PathVariable String id) {
		return users.get(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		int id = ids.incrementAndGet();
		user.setId(id);
		users.put(id, user);
		return user;
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public User update(@RequestBody User user) {
		users.put(user.getId(), user);
		return user;
	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public User delete(@RequestBody User user) {
		users.remove(user.getId());
		return user;
	}
}
