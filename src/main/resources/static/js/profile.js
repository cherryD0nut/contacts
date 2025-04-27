// 파일 첨부 시 로컬 파일 사진 미리보기
const photoInput = document.getElementById("photoInput");
const previewBox = document.getElementById("preview-box");
const saveBtn = document.getElementById("saveBtn");
const editProfileModal = new bootstrap.Modal(document.getElementById('editProfileModal'));

previewBox.addEventListener('click', function () {
    editProfileModal.show();
});

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

const userId = $("#userId").text();
console.log("userId: ", userId);

// ------------------------------- 프로필 사진 변경 -------------------------------
function profileImageUpload(){

    let formData = new FormData();
    formData.append("photoInput", photoInput.files[0]);

    $.ajax({
        type: "post",
        url: "/profile/upload",
        data: formData,
        contentType: false,
        processData: false,
        success: function () {
            alert("프로필을 저장했습니다.");
        },
        error: function () {
            alert("네트워크 오류가 발생했습니다.");
        }
    });
}

saveBtn.addEventListener('click', function (e) {
    e.preventDefault(); // 기본 form 제출 막기
    profileImageUpload();
});
