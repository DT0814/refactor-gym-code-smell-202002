package cc.xpbootcamp.code_smell_kit.$02_duplicated_code;

import lombok.val;

public class MockController {
    @GetMapping("/lines")
    public ResponseEntity listFromUI(@PathVariable Long id) {
        val list = mockService.list(id);
        val response = map(list, mockName::fromEntity);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/lines-ci")
    public ResponseEntity listFromCI(@PathVariable Long id) {
        val list = mockService.list(id);
        val response = map(list, mockName::fromEntity);

        return ResponseEntity.ok().body(response);
    }

}
