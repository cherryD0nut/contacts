// 파일 첨부 시 로컬 파일 사진 미리보기
const photoInput = document.getElementById("photoInput");
const previewBox = document.getElementById("preview-box");

// ------------------------------- 업로드 이미지 미리보기 -------------------------------
function imagePreview(file) {
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            previewBox.innerHTML = `<img src="${e.target.result}">`;
        };
        reader.readAsDataURL(file);
    }
}
photoInput.addEventListener('change', function () {
    const file = this.files[0];
    imagePreview(file);
});

previewBox.addEventListener('click', function () {
    photoInput.click();
});

var principalId = '<sec'

console.log("principalId ", principalId);

// ------------------------------- 프로필 사진 변경 -------------------------------
function profileImageUpload(){

    $('#photoInput').on('click', function() {
        let photoInput = $('#photoInput')[0].files[0];
        if (!photoInput) {
            alert('파일을 선택하세요.');
            return;
        }

        let formData = new FormData();
        formData.append("photoInput", photoInput);

        $.ajax({
            type: "put",
            url: "/user/"+principalId+"/profileImageUrl",
            data: formData,
            contentType: false, //필수  x-www-form-urlencoded로 파싱됨
            processData: false, //필수 : contentType: false일 경우 processData가 쿼리 스트링으로 자동 설정되므로, false로 지정하여 해제함
            enctype: "multipart/form-data", // 필수 아님
            dataType: "json"
        }).done(res=>{

            // 사진 전송 성공시 이미지 변경
            let reader = new FileReader();
            reader.onload = (e) => {
                $("#preview-box").attr("src", e.target.result);
            }
            reader.readAsDataURL(photoInput); // 이 코드 실행시 reader.onload 실행됨.
        });
    });
}