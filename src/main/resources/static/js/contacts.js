document.addEventListener('DOMContentLoaded', function () {
    const editModal = document.getElementById('editContactModal');
    if (editModal) {
        editModal.addEventListener('show.bs.modal', function (event) {
            const button = event.relatedTarget;
            const id = button.getAttribute('data-id');
            const name = button.getAttribute('data-name');
            const email = button.getAttribute('data-email');
            const phone = button.getAttribute('data-phone');

            document.getElementById('edit-id').value = id;
            document.getElementById('edit-name').textContent = name;
            document.getElementById('edit-name-hidden').value = name;
            document.getElementById('edit-email').value = email;
            document.getElementById('edit-phone').value = phone;
        });
    }
});

function deleteContact(id) {
    if (!confirm("정말 삭제하시겠습니까?")) return;

    fetch(`/contacts/delete/${id}`, {
        method: 'POST',
        headers: {
            'X-Requested-With': 'XMLHttpRequest' // hey server, this is ajax request.
        }
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert("삭제되었습니다.");
            location.reload();
        } else {
            alert("삭제 중 에러가 발생했습니다.");
            console.error(data.error);
        }
    })
    .catch(error => {
        alert("서버 오류가 발생했습니다.");
        console.error(error);
    });
}