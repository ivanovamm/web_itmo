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


function check_values() {
    const x = document.getElementById("x_value").value;
    let y = undefined;
    let r = undefined;
    if (!(document.querySelector('input[name="optionY"]:checked') == null)) {
        y = document.querySelector('input[name="optionY"]:checked').value;
    } else {
        alert('Пожалуйста, выберете координату y.')
    }
    if (!(document.querySelector('input[name="optionR"]:checked') == null)) {
        r = document.querySelector('input[name="optionR"]:checked').value;
    } else {
        alert('Пожалуйста, выберете координату r.')
    }
    if (x === '') {
        alert('Пожалуйста, введите координату x.');
        return;
    }

    if (isNaN(Number(x)) || (x < -5 || x > 3)) {
        alert('Пожалуйста, введите корректное числовое значение x в диапазоне от -5 до 3.');
        return;
    }
    if (!isNaN(Number(y)) && !isNaN(Number(r))){
    send(x, y, r);}
}

function clear_table() {
    const tbody = table.querySelector('tbody');
    tbody.innerHTML = '';
}


async function send(x, y, r) {
    try {
        const response = await fetch("controller", {
            method: "GET",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: new URLSearchParams({
                "x": x,
                "y": y,
                "r": r
            })
        })

        if (!response.ok) {
            throw new Error(`Server responded with bad getaway status: ${response.status} ${await response.text()}`);
        }

        const serverAnswer = await response.json();
        return serverAnswer.isHit;
    } catch (error) {
        return null;
    }
}



