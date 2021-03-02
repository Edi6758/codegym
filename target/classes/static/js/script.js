const showModalDeletar = function(id) {
    $('#modalDeletar').modal('show')
    $('#id_deletar').val(id)
}

const deletar = function(path) {
    let id = $('#id_deletar').val()
    console.log("edi" + id)
    if (id !== 0) {
        window.location.href = '/' + path + '/delete/' + id
    }
}

//--------------------------------------
// TELA DE QUEM SOMOS