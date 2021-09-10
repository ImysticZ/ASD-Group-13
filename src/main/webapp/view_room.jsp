<html lang="en">
    <%@page import="java.util.*"%>
    <%@page import="uts.asd.model.*"%>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Rooms</title>
    <jsp:include page="nav.jsp" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        .card {
            position: relative;
            display: flex;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 1px solid rgba(0, 0, 0, .125);
            border-radius: .25rem
        }

        .card>hr {
            margin-right: 0;
            margin-left: 0
        }

        .card>.list-group {
            border-top: inherit;
            border-bottom: inherit
        }

        .card>.list-group:first-child {
            border-top-width: 0;
            border-top-left-radius: calc(.25rem - 1px);
            border-top-right-radius: calc(.25rem - 1px)
        }

        .card>.list-group:last-child {
            border-bottom-width: 0;
            border-bottom-right-radius: calc(.25rem - 1px);
            border-bottom-left-radius: calc(.25rem - 1px)
        }

        .card>.card-header+.list-group,
        .card>.list-group+.card-footer {
            border-top: 0
        }

        .card-body {
            flex: 1 1 auto;
            padding: 1rem 1rem
        }

        .card-title {
            margin-bottom: .5rem
        }

        .card-subtitle {
            margin-top: -.25rem;
            margin-bottom: 0
        }

        .card-text:last-child {
            margin-bottom: 0
        }

        .card-link+.card-link {
            margin-left: 1rem
        }

        .card-header {
            padding: .5rem 1rem;
            margin-bottom: 0;
            background-color: rgba(0, 0, 0, .03);
            border-bottom: 1px solid rgba(0, 0, 0, .125)
        }

        .card-header:first-child {
            border-radius: calc(.25rem - 1px) calc(.25rem - 1px) 0 0
        }

        .card-footer {
            padding: .5rem 1rem;
            background-color: rgba(0, 0, 0, .03);
            border-top: 1px solid rgba(0, 0, 0, .125)
        }

        .card-footer:last-child {
            border-radius: 0 0 calc(.25rem - 1px) calc(.25rem - 1px)
        }

        .card-header-tabs {
            margin-right: -.5rem;
            margin-bottom: -.5rem;
            margin-left: -.5rem;
            border-bottom: 0
        }

        .card-header-pills {
            margin-right: -.5rem;
            margin-left: -.5rem
        }

        .card-img-overlay {
            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            padding: 1rem;
            border-radius: calc(.25rem - 1px)
        }

        .card-img,
        .card-img-bottom,
        .card-img-top {
            width: 100%
        }

        .card-img,
        .card-img-top {
            border-top-left-radius: calc(.25rem - 1px);
            border-top-right-radius: calc(.25rem - 1px)
        }

        .card-img,
        .card-img-bottom {
            border-bottom-right-radius: calc(.25rem - 1px);
            border-bottom-left-radius: calc(.25rem - 1px)
        }

        .card-group>.card {
            margin-bottom: .75rem
        }

        @media (min-width:576px) {
            .card-group {
                display: flex;
                flex-flow: row wrap
            }

            .card-group>.card {
                flex: 1 0 0%;
                margin-bottom: 0
            }

            .card-group>.card+.card {
                margin-left: 0;
                border-left: 0
            }

            .card-group>.card:not(:last-child) {
                border-top-right-radius: 0;
                border-bottom-right-radius: 0
            }

            .card-group>.card:not(:last-child) .card-header,
            .card-group>.card:not(:last-child) .card-img-top {
                border-top-right-radius: 0
            }

            .card-group>.card:not(:last-child) .card-footer,
            .card-group>.card:not(:last-child) .card-img-bottom {
                border-bottom-right-radius: 0
            }

            .card-group>.card:not(:first-child) {
                border-top-left-radius: 0;
                border-bottom-left-radius: 0
            }

            .card-group>.card:not(:first-child) .card-header,
            .card-group>.card:not(:first-child) .card-img-top {
                border-top-left-radius: 0
            }

            .card-group>.card:not(:first-child) .card-footer,
            .card-group>.card:not(:first-child) .card-img-bottom {
                border-bottom-left-radius: 0
            }
        }

        .accordion-button {
            position: relative;
            display: flex;
            align-items: center;
            width: 100%;
            padding: 1rem 1.25rem;
            font-size: 1rem;
            color: #212529;
        }
    </style>
</head>
<%
    ArrayList<RoomType> rooms = new ArrayList<RoomType>();
    rooms.add(new RoomType(0, 149.99, 1, "Junior Suite", "A small and cosy room"));
    rooms.add(new RoomType(1, 199.99, 2, "Regular Suite", "A nice room"));
    rooms.add(new RoomType(2, 229.99, 2, "Deluxe Suite", "A fancy room"));
    rooms.add(new RoomType(3, 259.99, 3, "Executive Suite", "A nice room for executives"));
    rooms.add(new RoomType(4, 459.99, 6, "Villa", "A large villa"));
    rooms.add(new RoomType(5, 999.99, 15, "Penthouse Suite", "The best and fanciest room"));
%>

<body>
    <div class="row text-center container-fluid">
        <section class="container">
            <div class="product-quantity">
              <h3>Beds</h3>
              <input data-min="1" data-max="0" type="text" name="quantity" value="1" readonly="true"><div class="quantity-selectors-container">
                <div class="quantity-selectors">
                  <button type="button" class="increment-quantity" aria-label="Add one" data-direction="1"><span>&#43;</span></button>
                  <button type="button" class="decrement-quantity" aria-label="Subtract one" data-direction="-1" disabled="disabled"><span>&#8722;</span></button>
                </div>
              </div>
            </div>
        </section>
        <p></p>
        <% for (RoomType roomType : rooms) { %>
        <div class="col-xl-3 col-md-4 col-sm-6 mb-4">
            <div class="card h-100 box-shadow">
                <h6 class="card-header text-muted"><%= roomType.getSuite()%></h6>
                <div class="card-body">
                    <p class="card-text">Price: $<%= roomType.getCost()%></p>
                    <p class="card-text"><%= roomType.getNumBeds()%> Bed</p>
                    <p class="card-text"><%= roomType.getDescription()%></p>
                </div>
                <div class="card-footer">
                    <a class="btn btn-outline-info" href="">More</a>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</body>
<script>
    $("button").on("click", function(ev) {
  var currentQty = $('input[name="quantity"]').val();
  var qtyDirection = $(this).data("direction");
  var newQty = 0;
  
  if (qtyDirection == "1") {
    newQty = parseInt(currentQty) + 1;
  }
  else if (qtyDirection == "-1") {
    newQty = parseInt(currentQty) - 1;
  }

  if (newQty == 1) {
    $(".decrement-quantity").attr("disabled", "disabled");
  }
  
  if (newQty > 1) {
    $(".decrement-quantity").removeAttr("disabled");
  }
  
  if (newQty > 0) {
    newQty = newQty.toString();
    $('input[name="quantity"]').val(newQty);
  }
  else {
    $('input[name="quantity"]').val("1");
  }
});
</script>

</html>