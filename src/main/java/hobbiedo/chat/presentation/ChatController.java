package hobbiedo.chat.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hobbiedo.chat.application.ChatService;
import hobbiedo.chat.dto.response.ChatHistoryListDTO;
import hobbiedo.chat.dto.response.ChatImageListDTO;
import hobbiedo.global.base.BaseResponse;
import hobbiedo.global.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/users/chat")
@RequiredArgsConstructor
@Tag(name = "Chat-mvc", description = "채팅(동기) API 입니다")
public class ChatController {
	private final ChatService chatService;

	@Operation(summary = "(특정 소모임의) 이전 채팅 내역 조회", description = "페이지에 따른 10개의 이전 채팅 내역을 조회한다.")
	@GetMapping("/history/{crewId}")
	public BaseResponse<List<ChatHistoryListDTO>> getChatHistoryBefore(@PathVariable Long crewId,
		@RequestParam int page, @RequestHeader(name = "Uuid") String uuid) {
		return BaseResponse.onSuccess(SuccessStatus.FIND_CHAT_HISTORY,
			chatService.getChatHistoryBefore(crewId, uuid, page));
	}

	@Operation(summary = "(특정 소모임) 사진 모아보기", description = "특정 소모임의 이미지 URL이 있는 채팅 내역을 조회한다.")
	@GetMapping("/image/{crewId}")
	public BaseResponse<List<ChatImageListDTO>> getChatsWithImageUrl(@PathVariable Long crewId) {
		return BaseResponse.onSuccess(SuccessStatus.FIND_IMAGE_CHAT,
			chatService.getChatsWithImageUrl(crewId));
	}

}
