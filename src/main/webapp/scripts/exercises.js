$(document).ready( function () {
    var groupColumn = 0;
    var table = $('#exercises').DataTable({
        scrollY: '500px',
        scrollCollapse: true,
        paging: false,
        columnDefs: [{ visible: false, targets: groupColumn }],
        order: [[groupColumn, 'asc']],
        displayLength: 25,
        drawCallback: function (settings) {
            var api = this.api();
            var rows = api.rows({ page: 'current' }).nodes();
            var last = null;
 
            api
                .column(groupColumn, { page: 'current' })
                .data()
                .each(function (group, i) {
                    if (last !== group) {
                        $(rows)
                            .eq(i)
                            .before('<tr class="group"><td colspan="5">' + group + '</td></tr>');
 
                        last = group;
                    }
                });
        },
    });
} );

let formContainer = document.querySelector('.edit-form')

let overlay = document.querySelector('.edit-form .overlay')

overlay.addEventListener('click',()=>{
    formContainer.style.display = 'none'
})

let edit = document.querySelectorAll('.edit-button')
edit.forEach(item=>{
    item.addEventListener('click',()=>{
        formContainer.style.display='flex'
    })
})

