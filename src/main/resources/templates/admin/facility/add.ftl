<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<@pt.dashboardPage>
<h1 class="page-header">Pridanie nového facilities</h1>

<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info btn-sm" href="<@spring.url '/admin/facility'/>" role="button">Naspäť</a>
    </div>
</div>

<br>

<div class="table-responsive">
    <form name="facility" action="<@spring.url '/admin/facility/save'/>" method="post">
        <div class="form-group">
            <label for="facility-id">ID</label>
            <input type="text" name="id" class="form-control" id="facility-id" placeholder="ID"
                   value="${facility.id!""}">
        </div>
        <div class="form-group">
            <label for="facility-name">Názov</label>
            <input type="text" name="name" class="form-control" id="facility-name" placeholder="Názov"
                   value="${facility.name!""}">
        </div>
        <div class="form-group">
            <label for="facility-ulica">Ulica</label>
            <input type="text" name="ulica" class="form-control" id="facility-ulica" placeholder="Ulica"
                   value="${facility.ulica!""}">
        </div>
        <div class="form-group">
            <label for="facility-cislo">Číslo</label>
            <input type="text" name="cislo" class="form-control" id="facility-cislo" placeholder="Číslo"
                   value="${facility.cislo!""}">
        </div>
        <div class="form-group">
            <label for="facility-psc">PSČ</label>
            <input type="text" name="psc" class="form-control" id="facility-psc" placeholder="PSČ"
                   value="${facility.psc!""}">
        </div>
        <div class="form-group">
            <label for="facility-mesto">Mesto</label>
            <input type="text" name="mesto" class="form-control" id="facility-mesto" placeholder="Mesto"
                   value="${facility.mesto!""}">
        </div>

        <div class="form-group">
            <div>
                <input type="submit" value="Ulož" class="btn btn-success">
            </div>
        </div>
    </form>
</div>
</@pt.dashboardPage>