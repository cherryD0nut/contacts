const photoInput = document.getElementById("photoInput");
const previewBox = document.getElementById("preview-box");
const saveBtn = document.getElementById("saveBtn");
const editProfileModal = document.querySelector('#editProfileModal');
const fileTypes = ['image/jpeg', 'image/png', 'image/gif', 'image/bmp', 'image/tiff'];


const userId = $("#userId").text();
console.log("userId: ", userId);

document.getElementById('uploadPhotoBtn').addEventListener('click', function () {
    photoInput.click();
    photoInput.addEventListener('change', function () {
        const file = this.files[0];

        // 파일 크기 확인
        const maxSize = 10 * 1024 * 1024;  //10MB
        const fileSize = file.size;
        if (fileSize > maxSize) {
            alert('최대 파일 크기는 ' + maxSize + 'MB 입니다.');
        } else {
            // 이미지 파일인지 확인
            if (file && fileTypes.includes(file.type)) {
                imagePreview(file);
                const modalInstance = bootstrap.Modal.getInstance(editProfileModal);
                modalInstance.hide();
            } else {
                alert("이미지 파일만 등록 가능합니다.")
            }
        }

    });
});

document.getElementById('deletePhotoBtn').addEventListener('click', function () {
    if (!confirm("정말 삭제하시겠습니까?")) return;
    previewBox.innerHTML = `<img th:src="@{/images/defaultUser.png}">`;
})

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

// ------------------------------- 프로필 정보 저장 -------------------------------
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


document.getElementById("cancelBtn").addEventListener('click', function () {
    if (previewBox) {
        previewBox.src = '';
    }
});