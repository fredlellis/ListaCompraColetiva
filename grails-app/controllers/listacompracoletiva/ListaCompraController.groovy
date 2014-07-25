package listacompracoletiva



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ListaCompraController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ListaCompra.list(params), model:[listaCompraInstanceCount: ListaCompra.count()]
    }

    def show(ListaCompra listaCompraInstance) {
        respond listaCompraInstance
    }

    def create() {
        respond new ListaCompra(params)
    }

    @Transactional
    def save(ListaCompra listaCompraInstance) {
        if (listaCompraInstance == null) {
            notFound()
            return
        }

        if (listaCompraInstance.hasErrors()) {
            respond listaCompraInstance.errors, view:'create'
            return
        }

        listaCompraInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'listaCompra.label', default: 'ListaCompra'), listaCompraInstance.id])
                redirect listaCompraInstance
            }
            '*' { respond listaCompraInstance, [status: CREATED] }
        }
    }

    def edit(ListaCompra listaCompraInstance) {
        respond listaCompraInstance
    }

    @Transactional
    def update(ListaCompra listaCompraInstance) {
        if (listaCompraInstance == null) {
            notFound()
            return
        }

        if (listaCompraInstance.hasErrors()) {
            respond listaCompraInstance.errors, view:'edit'
            return
        }

        listaCompraInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ListaCompra.label', default: 'ListaCompra'), listaCompraInstance.id])
                redirect listaCompraInstance
            }
            '*'{ respond listaCompraInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ListaCompra listaCompraInstance) {

        if (listaCompraInstance == null) {
            notFound()
            return
        }

        listaCompraInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ListaCompra.label', default: 'ListaCompra'), listaCompraInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'listaCompra.label', default: 'ListaCompra'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
