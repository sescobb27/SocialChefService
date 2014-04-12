package com.socialchef.service.repositories;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.socialchef.service.models.User;
import com.socialchef.service.repositories.implementation.UserServiceRepository;

public class UserServiceRepositoryTest {

	private UserServiceRepository userService;

    private UserRepository userRepositoryMock;
    
    @Before
    public void setUp() {
    	userService = new UserServiceRepository();

    	userRepositoryMock = mock(UserRepository.class);
        userService.setUserRepository(userRepositoryMock);
    }

    @Test
    public void findByUsernameTest() {
    	for (User u : User.mockUsers()) {
    		when(userRepositoryMock.findByUsername(u.getUsername()))
    			.thenReturn(u);
    		User actual = userService.findByUsername(u.getUsername());
    		verify(userRepositoryMock, times(1)).findByUsername(u.getUsername());
    		verifyNoMoreInteractions(userRepositoryMock);
    		assertEquals("Username Should be equals", u.getUsername(), actual.getUsername());
    	}
    }
}
