function uncheckAllR(checkbox) {
    var checkboxes = document.getElementsByName('optionR');
    checkboxes.forEach(function (current) {
        if (current !== checkbox) {
            current.checked = false;
        }
    });
}

const table = document.getElementById("table");


function check_values() {
    const x = document.getElementById("x_value").value;
    const y = document.getElementById("y_value").value;
    let r = undefined;
    if (!(document.querySelector('input[name="optionR"]:checked') == null)) {
        r = document.querySelector('input[name="optionR"]:checked').value;
    } else {
        alert('Пожалуйста, выберете координату r.')
    }
    if (x === '') {
        alert('Пожалуйста, введите координату x.');
        return;
    }

    if (y === '') {
        alert('Пожалуйста, введите координату y.');
        return;
    }

    if (isNaN(Number(x)) || (x < -2 || x > 2)) {
        alert('Пожалуйста, введите корректное числовое значение x в диапазоне от -2 до 2.');
        return;
    }
    if (isNaN(Number(y)) || (y < -5 || y > 3)) {
        alert('Пожалуйста, введите корректное числовое значение y в диапазоне от -5 до 3.');
        return;
    }
    if (!isNaN(Number(r))) {
        send(x, y, r);
    }
}

function clear_table() {
    const tbody = table.querySelector('tbody');
    tbody.innerHTML = '';
}


function send(x, y, r) {
    const form = $('<form>', {
        action: "controller",
        method: "get"
    });

    const args = {action: "submitForm", x: x, y: y, r: r};
    Object.entries(args).forEach(entry => {
        const [key, value] = entry;
        $('<input>').attr({
            type: "hidden",
            name: key,
            value: value
        }).appendTo(form);
    });

    form.appendTo('body').submit()
}



