function uncheckAllY(checkbox) {
    var checkboxes = document.getElementsByName('optionY');
    checkboxes.forEach(function (current) {
        if (current !== checkbox) {
            current.checked = false;
        }
    });
}

function uncheckAllR(checkbox) {
    var checkboxes = document.getElementsByName('optionR');
    checkboxes.forEach(function (current) {
        if (current !== checkbox) {
            current.checked = false;
        }
    });
}

const table = document.getElementById("table");


function check() {
    const x = document.getElementById("x_value").value;
    const y = document.querySelector('input[name="optionY"]:checked').value;
    const r = document.querySelector('input[name="optionR"]:checked').value;
    if (x == '') {
        alert('Пожалуйста, введите координату x.');
        return;
    }

    if (y == '') {
        alert('Пожалуйста, выберите координату y');
        return;
    }

    if (r == '') {
        alert('Пожалуйста, выберите R');
        return;
    }
    if (x < -5 || x > 3) {
        alert('Пожалуйста, введите корректное числовое значение x в диапазоне от -5 до 3.');
        return;
    }
    send(x, y, r);
}

function clear_table() {
    const tbody = table.querySelector('tbody');
    tbody.innerHTML = '';
}

function send(x, y, r) {
    $.ajax({
        url: 'check.php',
        method: 'GET',
        data: {r, x, y},
        success: function (data) {
            console.log(data)
            const tbody = table.querySelector('tbody');
            tbody.innerHTML +=data;
            },
        error: function () {
            alert('Произошла ошибка при отправке данных.');
        }
    });
}



