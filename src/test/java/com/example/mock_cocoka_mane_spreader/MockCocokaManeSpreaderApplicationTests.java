package com.example.mock_cocoka_mane_spreader;

import com.example.mock_cocoka_mane_spreader.controller.SpreadMoneyController;
import com.example.mock_cocoka_mane_spreader.model.SpreadMoneyBundleInfoDetail;
import com.example.mock_cocoka_mane_spreader.service.SpreadMoneyBundleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@WebMvcTest(controllers = SpreadMoneyController.class)
class MockCocokaManeSpreaderApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SpreadMoneyBundleService spreadMoneyBundleService;

	@Test
	public void spreadMoneyTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/spreadMoney/flex")
				.header("X-USER-ID", 1)
				.header("X-ROOM-ID", "abc")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"totalAmount\": 100, \"receiverNum\": 2 }"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void receiveMoneyTest() throws Exception {
		Mockito.when(spreadMoneyBundleService.addSpreadMoneyBundleInfoDetail("e1o", 2, "abc"))
				.thenReturn(50);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/spreadMoney/tap/e1o")
				.header("X-USER-ID", 2)
				.header("X-ROOM-ID", "abc"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("50"));
	}

	@Test
	public void checkMoneyTest() throws Exception {
		var ret = new ArrayList<SpreadMoneyBundleInfoDetail>();
		ret.add(new SpreadMoneyBundleInfoDetail("e1o", 50, 2));

		Mockito.when(spreadMoneyBundleService.getSpreadMoneyBundleInfoDetailStatuses("e1o", 1))
				.thenReturn(ret);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/spreadMoney/status/e1o")
				.header("X-USER-ID", 1))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("[{\"token\":\"e1o\",\"amount\":50,\"receiverId\":2}]"));
	}
}
