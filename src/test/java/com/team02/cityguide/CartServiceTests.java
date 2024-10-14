package com.team02.cityguide;

import com.team02.cityguide.entity.UserSpotEntity;
import com.team02.cityguide.repository.CartSpotRepository;
import com.team02.cityguide.repository.RouteLikeRepository;
import com.team02.cityguide.repository.SpotLikeRepository;
import com.team02.cityguide.repository.UserSpotRepository;
import com.team02.cityguide.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CartServiceTests {
    @Mock
    private CartSpotRepository cartSpotRepository;

    @Mock
    private UserSpotRepository userSpotRepository;

    @Mock
    private RouteLikeRepository routeLikeRepository;

    @Mock
    private SpotLikeRepository spotLikeRepository;

    private CartService cartService;

    @BeforeEach
    public void setUp() {
        cartService = new CartService(cartSpotRepository, userSpotRepository, routeLikeRepository, spotLikeRepository);
    }

    @Test
    void getCart_test(){
        List<UserSpotEntity> userSpotEntities = List.of(
                new UserSpotEntity(1L, 1L, null, null, null, null, null, null, null, null, null, null, null, null, null, null)
        );
    }

    @Test
    void addSpotToCartTest_whenOrderNotExist() {

    }

    @Test
    void addSpotToCartTest_whenOrderExist() {

    }
}
